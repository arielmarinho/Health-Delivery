package com.ariel.healthdelivery.model

import android.provider.ContactsContract
import java.lang.reflect.Constructor

class  Order {
 //   var user_id: String? = null
    var order_id: String?= null
    var name: String? = null
    var min: Double? = null
    var max: Double?= null
    var phone: String? = null

    constructor()
    constructor(order_id: String?, name: String?, min: Double?, max: Double?, phone: String?) {
        //user_id: String?,
       // this.user_id = user_id
        this.order_id = order_id
        this.name = name
        this.min = min
        this.max = max
        this.phone = phone
    }
}
