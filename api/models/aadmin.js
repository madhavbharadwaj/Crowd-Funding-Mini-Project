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
const aadminSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    email: 
    { 
        type: String, 
        required: true, 
        unique: true,
        //email regex (email validation)
        match: /^[a-zA-Z0-9_.+-]+@(?:(?:[a-zA-Z0-9-]+\.)?[a-zA-Z]+\.)?(bmsce.ac)\.in$/
    },
    f_name: { type: String, required: false },
    m_name: { type: String, required: false },
    l_name: { type: String, required: false },
    username: { type: String, required: false },
    phone: { type: Number, required: false },
    creation_time : { type : String, default: minuteFromNow },
    lastLogin :{ type : String, default: minuteFromNow },
    reset_password_token: {type: String},
    reset_password_expires: {type: Date},
    tokky: {type: String},
    password: { type: String, required: false },
    
   
});


aadminSchema.methods.comparePassword = function(password) {
    return bcrypt.compareSync(password, this.hash_password);
  };

module.exports = mongoose.model('Aadmin', aadminSchema);

