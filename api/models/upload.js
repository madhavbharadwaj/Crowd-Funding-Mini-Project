const mongoose = require('mongoose');

var minuteFromNow = function(){
    var d = new Date();
     d.setHours(d.getHours() + 5);
   d.setMinutes(d.getMinutes() + 30);
     var n = d.toLocaleString();
   return n;
  };


const uploadSchema = mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    //email: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
    email: { type: String, ref: 'Student',required: true },
    title : {type: String,required:true},
    git_proj_link:{type: String,required:true},
    description:{type: String,required:true},
    domain : {type: String,required:true},
    category : {type: String,required:true},
    message : {type: String,required:false},
    upload_time : { type : String, default: minuteFromNow }
});

module.exports = mongoose.model('Upload', uploadSchema);

