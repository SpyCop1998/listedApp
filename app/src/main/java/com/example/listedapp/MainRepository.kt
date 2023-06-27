package com.example.listedapp

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getData(bearerToken:String):NetworkState<ResponseModel>{
        val response = retrofitService.getData(bearerToken)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}