import os
import string

from pathlib import Path
from django.shortcuts import render
from django.conf import settings
from django.views.decorators.csrf import csrf_exempt
import requests
from django.http import JsonResponse
import json
from pyd2dplus import D2DPlus
from pyd2dplus.Measurement import Measurement

from requests import Response

conn = D2DPlus.connect(settings.D2D_PATH)
measurementData = []
result = False
outputTagNoArr = []
testId = ""
testName = ""
host = ""
port = ""
ftpUsername = ""
ftpPassword = ""
rootPath = ""
type = ""
outputTagName = ""
inputTagNos = []

BASE_DIR = Path(__file__).resolve().parent.parent
# This method used for rendering tag numbers html page
def tag_nos(request):
    return render(request, 'tagnos.html', {'output_tag_no_arr': outputTagNoArr})


# This method used for rendering tag number,measurement search by parameters html page
def tag_no_search(request):
    return render(request, 'tagnosearch.html')


# This method is used to catch url parameters for further use in application
def search(request):
    global testId, testName, host, port, ftpUsername, ftpPassword, rootPath, type, outputTagNoArr, outputTagName
    outputTagNo = request.GET.get('outputtagno')
    outputTagName = request.GET.get('outputdataname')
    testId = request.GET.get('testid')
    testName = request.GET.get('testname')
    host = request.GET.get('host')
    port = request.GET.get('port')
    ftpUsername = request.GET.get('ftpusername')
    ftpPassword = request.GET.get('ftppassword')
    rootPath = request.GET.get('rootpath')
    type = request.GET.get('type')
    outputTagNoArr = []
    if "," in outputTagNo:
        outputTagNoArr = outputTagNo[0:len(outputTagNo)].split(",")
    else:
        outputTagNoArr.insert(0, outputTagNo)
    return render(request, 'search.html')


# This method is used to get attribute values of specific nodes
@csrf_exempt
def get_node_list(request):
    try:
        label = request.POST['label']
        attr = request.POST['attribute']
        data = conn.getList(label, attr)
        return JsonResponse(data)
    except Exception as e:
        print(e)


# This method is used to get measurement result through search criteria aaplied
@csrf_exempt
def get_measurement_by_search(request):
    try:
        search_criteria = json.loads(request.body)
        data = conn.getMeasurementBySearch(search_criteria)
        return JsonResponse(data)
    except Exception as e:
        raise Exception(e)
    return data


# This method is used to pass selected measurement data to the next page
@csrf_exempt
def get_measurement_data(request):
    try:
        data = json.loads(request.body)
        global measurementData
        measurementData = []
        measurementData = data
        global result
        if measurementData is not None:
            result = True
        return JsonResponse({'result': result})
    except Exception as e:
        print(e)


# This method is used to render page on which selected measurement details
# displayed along with output tag number selection in grid
def get_measurement(request):
    return render(request, 'measurement.html',
                  {'measurement_data': measurementData, 'output_tag_no_arr': outputTagNoArr,
                   'output_tag_name': outputTagName})


# This method is used to merge tag number and generate atfx file from combined tag number and measurement
@csrf_exempt
def merge_tag_number(request):
    try:
        data = json.loads(request.body)
        global rootPath
        fileTransferMode = {'password': ftpPassword, 'port': port, 'host': host, 'type': type, 'rootpath': rootPath,
                            'username': ftpUsername}
        mea_list = []
        for me in data:
            mea1 = Measurement()
            mea1.set_url(me['measurementDataURL'])
            mea1.set_name(me['measurementName'])
            mea1.set_out_put_tags(me['outputTagNo'])
            mea1.set_out_put_data_names(me['outputTagDataName'])
            mea_list.append(mea1)

        res = conn.combineTagNoMeasurement(testId, testName, mea_list, fileTransferMode)
        return JsonResponse(res)
    except Exception as e:
        print(e)


# This method is used to load selected input tag number for further usage in application
# This method is not currently in use
@csrf_exempt
def merge_input_tag_number(request):
    try:
        data = json.loads(request.body)
        global inputTagNos
        inputTagNos = data
        if data is not None:
            res = True
        else:
            res = False
        return JsonResponse({'result': res})
    except Exception as e:
        print(e)


# This method return selected stored tag number for displaying to user in tag number combo
# This method is not currently in use
@csrf_exempt
def get_input_tag_number(request):
    try:
        global inputTagNos
        print(inputTagNos)
        return JsonResponse({'data': inputTagNos})
    except Exception as e:
        print(e)


# This method is used to read search parameter json file
@csrf_exempt
def get_search_parameters(request):
    try:
        path = os.path.join(BASE_DIR, 'search.json')
        print(path)
        with open(path) as jsonfile:
            json_data = json.loads(jsonfile.read())
        print(json_data)
        return JsonResponse(json_data)
    except Exception as e:
        print(e)

