package com.sun.training.ut.data.model

import com.sun.training.ut.data.Constants

data class No8Member(val age: Int = 0,
                     val gender: Constants.Gender = Constants.Gender.MALE) : BaseModel()