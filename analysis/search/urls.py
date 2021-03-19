"""analysis URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from . import views

urlpatterns = [
    path('', views.search, name='search'),
    path('tagNos', views.tag_nos, name="tag_nos"),
    path('tagNoSearch', views.tag_no_search, name='tag_no_search'),
    path('getNodeList', views.get_node_list, name='get_node_list'),
    path('getMeasurementBySearch', views.get_measurement_by_search, name='get_measurement_by_search'),
    path('getMeasurementData', views.get_measurement_data, name='get_measurement_data'),
    path('measurement', views.get_measurement, name='get_measurement'),
    path('mergeTagNumber', views.merge_tag_number, name='merge_tag_number'),
    path('mergeInputTagNumber', views.merge_input_tag_number, name='merge_input_tag_number'),
    path('getInputTagNosList', views.get_input_tag_number, name='get_input_tag_number'),
    path('getSearchParameters', views.get_search_parameters, name='get_search_parameters'),
]
