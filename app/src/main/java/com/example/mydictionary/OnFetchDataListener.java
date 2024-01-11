package com.example.mydictionary;

import com.example.mydictionary.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
