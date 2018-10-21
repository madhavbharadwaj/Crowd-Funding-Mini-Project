const express = require("express");
const router = express.Router();
const mongoose = require("mongoose");
const Student = require("../models/student");
const Upload = require("../models/upload");

//space inst hosp

var minuteFromNow = function(){
    var d = new Date();
     d.setHours(d.getHours() + 5);
   d.setMinutes(d.getMinutes() + 30);
     var n = d.toLocaleString();
   return n;
  };


//upload a project [passed by email]
//http://localhost:3000/upload/project/email/


router.post("/project/email/:emailId", (req, res, next) => {
  //const email = req.params.emailId;
  Student.findOne({email:req.params.emailId})
    .then(email => {
      if (!email) {
        return res.status(404).json({
          message: "student not found for provided email"
        });
      }
      const upload = new Upload({
        _id: mongoose.Types.ObjectId(),
        git_proj_link: req.body.git_proj_link,
        description: req.body.description,
        domain: req.body.domain,
        category: req.body.category,
        title: req.body.title,
        email: req.params.emailId,
      });
      return upload.save();
    })
    .then(result => {
      console.log(result),
      Upload.updateOne,({email:req.params.email},{$set : { upload_time : minuteFromNow()}},function(err) {
        if(err) 
        {
           throw err;
        }
       } )
      res.status(201).json
      ({
        message: " PROJECT UPLOADED SUCCESSFULLY [passed by email]"
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});


module.exports = router;