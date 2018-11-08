const express = require("express");
const router = express.Router();
const mongoose = require("mongoose");
const Student = require("../models/student");
const Upload = require("../models/upload");
const async = require("async");
const nodemailer = require("nodemailer");
//env
const dotenv = require("dotenv");

email = process.env.MAILER_EMAIL_ID
pass = process.env.MAILER_PASSWORD


require('dotenv').config()

jwt_key: process.env.JWT_KEY
mailer_email_id :process.env.MAILER_EMAIL_ID
mailer_password :process.env.MAILER_PASSWORD
mailer_service_provider:process.env.MAILER_SERVICE_PROVIDER

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


//to get all students projects yet to be Approve [pending projects]
//http://localhost:3000/upload/pending/

router.get("/pending", (req, res, next) => {
  const status = "pending";
  Upload.find({status})
  
    .select('email id title git_proj_link description domain category upload_time status')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) 
      {
        res.status(200).json({
            PROJECT_STATUS : status,
            TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED: doc.length,
            COMPLETE_DETAILS: doc
        });
      }
       else {
        res
          .status(404)
          .json({ message: "No projects found under provided the status" });
      }
      
    })
    .catch
    (err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
  });
  



//to get all students projects which are Approve [Approve projects]
//http://localhost:3000/upload/approve


router.get("/approve", (req, res, next) => {
  const status = "Approve";
  Upload.find({status})
  .select("email _id title git_proj_link description category domain upload_time status")
  //.populate('email')
  .exec()
  .then(doc => {
     console.log("From database", doc);
      if (doc) 
      {
        res.status(200).json({
            PROJECT_STATUS : status,
            TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED: doc.length,
            COMPLETE_DETAILS: doc
        });
      }
       else {
        res
          .status(404)
          .json({ message: "No projects found under provided the status" });
      }
      
    })
    .catch
    (err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
  });


  //EMAIL CONFIGURATION

const smtpTransport = nodemailer.createTransport({
  service: process.env.MAILER_SERVICE_PROVIDER ,
  auth: {
    user: email,
    pass: pass 
  }
});

//API TO APPROVE PROJECT (passing email)

router.put("/edit/:studentEmail", (req, res, next) => {
  const email = req.params.studentEmail; 
  Upload.updateOne({email}, req.body)
    .exec()
    .then(result =>{
      console.log(result);
      async.waterfall([
        function(done) {
          Upload.findOne({
            email:  req.params.studentEmail
          }).exec(function(err, upload) {
            if (upload) {
              done(err, upload);
            } else {
              done('student not found.');
            }
          });
        },
        function(upload, done) {
          var data = {
            to: upload.email,
            from: email,
            subject: 'CROWD SOURCE\n\n',
            text: 'YOUR PROJECT HAS BEEN APPROVED\n\n' 
           
          };
          
          smtpTransport.sendMail(data, function(err) {
            if (!err) {
              return res.json({ message: 'Kindly check your email' });
            } 
            else 
            {
              return done(err);
            }
          });
        }
      ], function(err) 
      {
        return res.status(422).json({ message: err });
      });
    res.status(201).json({
      message: "UPLOAD PROJECT SUCCESSFULL, MAIL HAS BEEN SENT TO PROFILE"
    });
  }).catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});



 //API TO DECLINE PROJECT (passing id)
 //http://localhost:3000/upload/delete/


 router.delete("/delete/:studentEmail", (req, res, next) => {
  const email = req.params.studentEmail;
 
  Upload.deleteOne({email})
    .exec()
    .then(result =>{
      console.log(result);
      async.waterfall([
        function(done) {
          Upload.findOne({
            email:  req.params.studentEmail
          }).exec(function(err, upload) {
            if (upload) {
              done(err, upload);
            } else {
              done('student not found.');
            }
          });
        },
        function(upload, done) {
          var data = {
            to: upload.email,
            from: email,
            subject: 'CROWD SOURCE\n\n',
            text: 'YOUR PROJECT HAS BEEN DECLINED\n\n' 
           
          };
          
          smtpTransport.sendMail(data, function(err) {
            if (!err) {
              return res.json({ message: 'Kindly check your email' });
            } 
            else 
            {
              return done(err);
            }
          });
        }
      ], function(err) 
      {
        return res.status(422).json({ message: err });
      });
    res.status(201).json({
      message: "DECLINED"
    });
  }).catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
    
});



/*MOBILE APPS*/ 


//to get particular students all uploaded projects yet to be Approve (PENDING)
//http://localhost:3000/upload/penstatus/


router.get("/penstatus/:studentEmail", (req, res, next) => {
  email =req.params.studentEmail;
  const status = "pending";
  Upload.find({email,status})
    .select('email id title git_proj_link description domain category upload_time status')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) 
      {
        res.status(200).json({
          STUDENT : email,
          PROJECT_STATUS : status,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED: doc.length,
          COMPLETE_DETAILS: doc
        });
      }
       else {
        res
          .status(404)
          .json({ message: "No projects found for provided email" });
      }
      
    })
    .catch
    (err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});

/*MOBILE APPS*/ 

//to get particular students all uploaded projects which are Approve 
//http://localhost:3000/upload/appstatus/


router.get("/appstatus/:studentEmail", (req, res, next) => {
  email =req.params.studentEmail;
  const status = "Approve";
  Upload.find({email,status})
    .select('email id title git_proj_link description domain category upload_time status')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) 
      {
        res.status(200).json({
          STUDENT : email,
          PROJECT_STATUS : status,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED: doc.length,
          COMPLETE_DETAILS: doc
        });
      }
       else {
        res
          .status(404)
          .json({ message: "No projects found for provided email" });
      }
      
    })
    .catch
    (err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});

//to get all students projects which are Approve based on category (ICL/MCA)
//http://localhost:3000/upload/appcategory/

router.get("/appcategory/:studentCategory", (req, res, next) => {
  category =req.params.studentCategory;
  const status = "Approve";
  Upload.find({category,status})
  .select('email id title git_proj_link description domain category upload_time status')
  .exec()
  .then(doc => {
    console.log("From database", doc);
    if (doc) 
    {
      res.status(200).json({
          PROJECT_STATUS : status,
          PROJECT_CATEGORY : category,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_CATEGORY: doc.length,
          COMPLETE_DETAILS: doc
      });
    }
     else {
      res
        .status(404)
        .json({ message: "No projects found under provided the CATEGORY" });
    }
    
  })
  .catch
  (err => {
    console.log(err);
    res.status(500).json({ error: err });
  });
});

//to get all students which are pending based on category (ICL/MCA)
//http://localhost:3000/upload/pencategory/

router.get("/pencategory/:studentCategory", (req, res, next) => {
  category =req.params.studentCategory;
  const status = "pending";
  Upload.find({category,status})
  .select('email id title git_proj_link description domain category upload_time status')
  .exec()
  .then(doc => {
    console.log("From database", doc);
    if (doc) 
    {
      res.status(200).json({
          PROJECT_STATUS : status,
          PROJECT_CATEGORY : category,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_CATEGORY: doc.length,
          COMPLETE_DETAILS: doc
      });
    }
     else {
      res
        .status(404)
        .json({ message: "No projects found under provided the CATEGORY" });
    }
    
  })
  .catch
  (err => {
    console.log(err);
    res.status(500).json({ error: err });
  });
});



//to get all students projects yet to be Approve based on DOMAIN 
//Artificial Intelligence (AI) Internet of Things (IoT) Enterprise Resource Planning (ERP) Machine Learning (ML)

//http://localhost:3000/upload/appdomain/

router.get("/appdomain/:studentDomain", (req, res, next) => {
  domain =req.params.studentDomain;
  const status = "Approve";
  Upload.find({domain,status})
  .select('email id title git_proj_link description domain category upload_time status')
  .exec()
  .then(doc => {
    console.log("From database", doc);
    if (doc) 
    {
      res.status(200).json({
          PROJECT_STATUS : status,
          PROJECT_DOMAIN : domain,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_DOMAIN: doc.length,
          COMPLETE_DETAILS: doc
      });
    }
     else {
      res
        .status(404)
        .json({ message: "No projects found under provided the DOMAIN" });
    }
    
  })
  .catch
  (err => {
    console.log(err);
    res.status(500).json({ error: err });
  });
});


//to get all students projects yet to be Approve based on DOMAIN 
//Artificial Intelligence (AI) Internet of Things (IoT) Enterprise Resource Planning (ERP) Machine Learning (ML)

//http://localhost:3000/upload/pendomain/

router.get("/pendomain/:studentDomain", (req, res, next) => {
  domain =req.params.studentDomain;
  const status = "pending";
  Upload.find({domain,status})
  .select('email id title git_proj_link description domain category upload_time status')
  .exec()
  .then(doc => {
    console.log("From database", doc);
    if (doc) 
    {
      res.status(200).json({
          PROJECT_STATUS : status,
          PROJECT_DOMAIN : domain,
          TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_DOMAIN: doc.length,
          COMPLETE_DETAILS: doc
      });
    }
     else {
      res
        .status(404)
        .json({ message: "No projects found under provided the DOMAIN" });
    }
    
  })
  .catch
  (err => {
    console.log(err);
    res.status(500).json({ error: err });
  });
});
module.exports = router;