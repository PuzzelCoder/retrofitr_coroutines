package com.example.coroutineretrofit1.model;

import java.util.ArrayList;

data class Owner(val avatar_url:String)
data class RecyclerData(val name:String,val description:String,val owner:Owner)
data class RecylerList(val items:ArrayList<RecyclerData>)
