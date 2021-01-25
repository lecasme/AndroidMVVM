package com.mvvm.domain.models

data class Response(val count:Int, val next: String, val previous: String, val results : List<Pokemon>)