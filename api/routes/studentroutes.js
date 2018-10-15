const express = require("express");
const router = express.Router();
const body = require("body-parser");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const Student = require("../models/student");
const async = require("async");
const nodemailer = require("nodemailer");
const crypto = require("crypto");
const path = require('path');


//env
const dotenv = require("dotenv");

email = process.env.MAILER_EMAIL_ID,
pass = process.env.MAILER_PASSWORD,


require('dotenv').config()

jwt_key: process.env.JWT_KEY
mailer_email_id :process.env.MAILER_EMAIL_ID
mailer_password :process.env.MAILER_PASSWORD
mailer_service_provider:process.env.MAILER_SERVICE_PROVIDER

//time updation


var minuteFromNow = function(){
  var d = new Date();
   d.setHours(d.getHours() + 5);
 d.setMinutes(d.getMinutes() + 30);
   var n = d.toLocaleString();
 return n;
};


//API TO ADD STUDENT PROFILE

router.post("/signup", (req, res, next) => {
 
  Student.find({ email: req.body.email })
  .exec()
  .then(student => {
    if (student.length >= 1) {
      return res.status(409).json({
        message: "Mail exists"
      });
    } else 
    {
       
          const student = new Student({
            _id: new mongoose.Types.ObjectId(),
            email: req.body.email,
            mobile:req.body.mobile,
            username:req.body.username,
            firstname: req.body.firstname,
            middlename: req.body.middlename,
            lastname: req.body.lastname,
            github: req.body.github,
            usn: req.body.usn
          });
         // student.password = bcrypt.hashSync(req.body.password, 10);
         
          student.save()
            .then(result => {
              console.log(result),
              Student.updateOne({email:req.body.email},{$set : { creation_time : minuteFromNow()}},function(err) {
                  if(err) 
                  {
                     throw err;
                  }
                 } )
                 async.waterfall([
                  function(done) {
                      Student.findOne({
                      email: req.body.email
                    }).exec(function(err, student) {
                      if (student) {
                        done(err, student);
                      } else {
                        done('Student not found.');
                      }
                    });
                  },
                  function(student, done) {
                    // create the random token
                    crypto.randomBytes(20, function(err, buffer) {
                      const token = buffer.toString('hex');
                      done(err, student, token);
                    });
                  },
                  function(student, token, done) {
                      Student.findByIdAndUpdate({ _id: student._id }, { reset_password_token: token, reset_password_expires: Date.now() + 86400000 }, { upsert: true, new: true }).exec(function(err, new_student) {
                      done(err, token, new_student);
                    });
                  },
                  function(token, student, done) {
                    var data = {
                      to: student.email,
                      from: email,
                      subject: 'CROWD SOURCE Password Reset',
                      text: 'Click the below link to reset\n\n' +
                      'http://' + req.headers.host + '/student/reset?token=' + token + '\n\n'
                    };
                   
                    smtpTransport.sendMail(data, function(err) {
                      if (!err) {
                        
                        //return res.sendFile(path.resolve('./public/reset-password.html'));
                        return res.json({ message: 'Kindly check your email for further instructions' });
                      } else {
                        return done(err);
                      }
                    });
                  }
                ], function(err) {
                  return res.status(422).json({ message: err });
                });
              res.status(201).json({
                message: "STUDENT PROFILE CREATED, MAIL HAS BEEN SENT TO YOUR REGISTERED ACCOUNT TO SET PASSWORD"
              });
            })
            


            
            .catch(err => {
              console.log(err);
              res.status(500).json({
                error: err
              });
            });
        }
  });
});





//API TO LOGIN STUDENT PROFILE

router.post("/login", (req, res, next) => {
  Student.find({ email: req.body.email })
  .exec()
  .then(student => {
    if (student.length < 1) {
      return res.status(401).json({
        message: "Auth failed"
      });
    }
    bcrypt.compare(req.body.password, student[0].password, (err, result) => {
      if (err) {
        return res.status(401).json({
          message: "Auth failed"
        });
      }
      
      if (result) {
        const success_token = jwt.sign
        (
          {
            email: student[0].email,
            student: student[0]._id
          },
         process.env.JWT_KEY,
          {
              expiresIn: "1h"
          }
        );
         
        if (result) {
          Student.update({email:req.body.email},{$set : { lastLogin : minuteFromNow()}},function(err) {
              if(err) 
              {
                 throw err;
              }
             } )
            

             Student.update({email:req.body.email},{tokky:success_token},function(err) {
              if(err) 
              {
                 throw err;
              }
             } )

             
        return res.status(200).json({
          message: "Auth successful",
         success_token: success_token
        });
      }
     }
      res.status(401).json({
        message: "Auth failed"
      });
    });
  })
  .catch(err => {
    console.log(err);
    res.status(500).json({
      error: err
    });
  });
});




















//API TO EDIT STUDENT PROFILE (passing email)

router.put("/email/:studentEmail", (req, res, next) => {
  const email = req.params.studentEmail; 
  Student.updateOne({email}, req.body)
    .exec()
    .then(result =>{
      console.log(result);
      res.status(200).json({
          message: 'Student details updated',
          request: {
              type: 'GET',
              url: 'http://localhost:3000/student/email/'+ email
          }
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});




//API TO EDIT STUDENT PROFILE (passing id)

router.put("/id/:studentId", (req, res, next) => {
  const id = req.params.studentId; 
  Student.updateOne({ _id: id }, req.body)
    .exec()
    .then(result =>{
      console.log(result);
      res.status(200).json({
          message: 'Student details updated',
          request: {
              type: 'GET',
              url: 'http://localhost:3000/student/id/'+ id
          }
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});




//API TO GET ALL STUDENT PROFILE DETAILS

router.get("/", (req, res, next) => {
    Student.find()
 .select("_id email username firstname middlename lastname mobile github usn creation_time lastLogin")
  .exec()
  .then(docs => {
    const response = {
      count: docs.length,
      student: docs.map(doc => {
        return {
            Student_details: doc,
          request: {
            type: "GET",
            url: "http://localhost:3000/student/"+ doc._id   
          }
        };
      })
    };
    
    res.status(200).json(response);
    
  })
  .catch(err => {
    console.log(err);
    res.status(500).json({
      error: err
    });
  });
});


//API TO GET PARTICULAR STUDENT PROFILE DETAILS (passing email)

router.get("/email/:studentEmail", (req, res, next) => {
  const email = req.params.studentEmail;
  Student.find({email})
    .select('_id email username firstname middlename lastname mobile github usn creation_time lastLogin')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) {
        res.status(200).json({
            Student_details: doc,
            request: {
                type: 'GET',
                url: 'http://localhost:3000/student/email'
            }
        });
      } else {
        res
          .status(404)
          .json({ message: "No Student found for provided ID" });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});





//API TO GET PARTICULAR STUDENT PROFILE DETAILS(passing id)

router.get("/id/:studentId", (req, res, next) => {
  const id = req.params.studentId;
  Student.findById(id)
    .select('_id email username firstname middlename lastname mobile github usn creation_time lastLogin')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) {
        res.status(200).json({
            Student_details: doc,
            request: {
                type: 'GET',
                url: 'http://localhost:3000/student/id'
            }
        });
      } else {
        res
          .status(404)
          .json({ message: "No student found for provided ID" });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});





//LOGOUT (DESTROY TOKEN)


router.get('/logout/:success_token', function(req, res) {
  Student.findOne
  ({
  tokky: req.params.success_token
  })
.exec(function(err, student) {
  if (!err &&student)
   {      
      student.tokky = undefined;
      
student.save(function(err) {
        if (err) {
          return res.status(422).send({
            message: err
          });
        } 

        else {
  if (!err) {
              return res.json({ message: 'TOKEN DESTROYED SUCCESSFULL' });
            } 
          
        }
      });

  } 
  
});
})
module.exports = router;




//API TO DELETE STUDENT PROFILE(passing email)

router.delete("/delete/:studentEmail", (req, res, next) => {
  const email = req.params.studentEmail;
  Student.remove({email})
    .exec()
    .then(result => {
      res.status(200).json({
          message: 'Student deleted',
          request: {
              type: 'POST',
              url: 'http://localhost:3000/student',
              
          }
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});


//API TO DELETE STUDENT PROFILE(passing id)

router.delete("/delete/:studentId", (req, res, next) => {
  const id = req.params.studentId;
  Student.remove({ _id: id })
    .exec()
    .then(result => {
      res.status(200).json({
          message: 'Student deleted',
          request: {
              type: 'POST',
              url: 'http://localhost:3000/student',
              
          }
      });
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({
        error: err
      });
    });
});


//EMAIL CONFIGURATION

const smtpTransport = nodemailer.createTransport({
  service: process.env.MAILER_SERVICE_PROVIDER,
  auth: {
    user: email,
    pass: pass
  }
});



//API TO FORGOT PASSWORD.

router.post("/forgot", (req, res, next) => {
  async.waterfall([
    function(done) {
        Student.findOne({
        email: req.body.email
      }).exec(function(err, student) {
        if (student) {
          done(err, student);
        } else {
          done('student not found.');
        }
      });
    },
    function(student, done) {
      // create the random token
      crypto.randomBytes(20, function(err, buffer) {
        const token = buffer.toString('hex');
        done(err, student, token);
      });
    },
    function(student, token, done) {
        Student.findByIdAndUpdate({ _id: student._id }, { reset_password_token: token, reset_password_expires: Date.now() + 86400000 }, { upsert: true, new: true }).exec(function(err, new_student) {
        done(err, token, new_student);
      });
    },
    function(token, student, done) {
      
      var data = {
        to: student.email,
        from: email,
        subject: 'CROWD SOURCE Password Reset',
        text: 'Click the below link to reset\n\n' +
        'http://' + req.headers.host + '/student/reset?token=' + token + '\n\n'
      };
      
     
      smtpTransport.sendMail(data, function(err) {
        if (!err) {
          
          //return res.sendFile(path.resolve('./public/reset-password.html'));
          return res.json({ message: 'Kindly check your email for further instructions' });
        } else {
          return done(err);
        }
      });
    }
  ], function(err) {
    return res.status(422).json({ message: err });
  });
});





//API TO RESET PASSWORD

router.get('/reset', function(req, res) {
  res.sendFile(path.resolve('./public/student.html'));
  //server.use(express.static(path.join(__dirname, './static')));
});

router.post("/reset", (req, res, next) => {
    Student.findOne({
    reset_password_token: req.body.token,
    reset_password_expires: {
      $gt: Date.now()
    }
  }
)
  .exec(function(err, student) {
    if (!err &&student) {
      
      if (req.body.newPassword === req.body.verifyPassword) {
        
        student.password = bcrypt.hashSync(req.body.newPassword, 10);
        student.reset_password_token = undefined;
        student.reset_password_expires = undefined;
        student.save(function(err) {
          if (err) {
            return res.status(422).send({
              message: err
            });
          } else {
            var data = {
              to: student.email,
              from: email,
          
              subject: 'Password Reset Confirmation',
              text: 'PASSWORD RESET WAS SUCCESSFULL'
            };

            smtpTransport.sendMail(data, function(err) {
              if (!err) {
                return res.json({ message: 'Password reset SUCCESSFULL' });
              } else {
                return done(err);
              }
            });
          }
        });
      } else {
        return res.status(422).send({
          message: 'Passwords do not match'
        });
      }
    } else {
      
      
      return res.status(400).send({
        
        message: 'Password reset token is invalid or has expired.'
      });
    }
  });
})


module.exports = router;



