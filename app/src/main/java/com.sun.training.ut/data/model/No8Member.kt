package com.sun.training.ut.data.model

import com.sun.training.ut.data.Constants

data class No8Member(var age: Int = 0,
                     var gender: Constants.Gender = Constants.Gender.MALE) : BaseModel()