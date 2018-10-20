const mongoose = require('mongoose');
const dotenv = require("dotenv");
const bcrypt = require('bcrypt'),
Schema = mongoose.Schema;


var minuteFromNow = function(){
    var d = new Date();
     d.setHours(d.getHours() + 5);
   d.setMinutes(d.getMinutes() + 30);
     var n = d.toLocaleString();
   return n;
  };


//ADMIN REQUIREMENTS
const adminSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,


    
    //BASIC

    email: 
    { 
        type: String, 
        required: true, 
        unique: true,
        //email regex (email validation ONLY BMSCE domain)
        match: /^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\.)?[a-zA-Z]+\.)?(bmsce.ac)\.in$/
    },
    username: { type: String, required: true },
    mobile: { type: Number, required: true },
    firstname: { type: String, required: false },
    middlename: { type: String, required: false },
    lastname: { type: String, required: false },
    password: { type: String, required: false },
    

    //ADDITIONAL
    creation_time : { type : String, default: minuteFromNow },
    lastLogin :{ type : String, default: minuteFromNow },
    tokky: {type: String},
    reset_password_token: {type: String},
    reset_password_expires: {type: Date}
});



adminSchema.methods.comparePassword = function(password) {
    return bcrypt.compareSync(password, this.hash_password);
  };
  
module.exports = mongoose.model('Admin', adminSchema);