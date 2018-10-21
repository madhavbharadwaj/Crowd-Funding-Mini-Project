const express = require("express");
const router = express.Router();
const body = require("body-parser");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const Admin = require("../models/admin");
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


//API TO ADD ADMIN PROFILE

router.post("/signup", (req, res, next) => {
 
  Admin.find({ email: req.body.email })
  .exec()
  .then(admin => {
    if (admin.length >= 1) {
      return res.status(409).json({
        message: "Mail exists"
      });
    } else 
    {
       
          const admin = new Admin({
            _id: new mongoose.Types.ObjectId(),
            email: req.body.email,
            mobile:req.body.mobile,
            username:req.body.username,
            firstname: req.body.firstname,
            middlename: req.body.middlename,
            lastname: req.body.lastname
          });
         // admin.password = bcrypt.hashSync(req.body.password, 10);
          admin.save()
          .then(result => {
            console.log(result),
            Admin.updateOne({email:req.body.email},{$set : { creation_time : minuteFromNow()}},function(err) {
                if(err) 
                {
                   throw err;
                }
               } )
               async.waterfall([
                function(done) {
                  Admin.findOne({
                    email: req.body.email
                  }).exec(function(err, admin) {
                    if (admin) {
                      done(err, admin);
                    } else {
                      done('ADMIN not found.');
                    }
                  });
                },
                function(admin, done) {
                  // create the random token
                  crypto.randomBytes(20, function(err, buffer) {
                    const token = buffer.toString('hex');
                    done(err, admin, token);
                  });
                },
                function(admin, token, done) {
                  Admin.findByIdAndUpdate({ _id: admin._id }, { reset_password_token: token, reset_password_expires: Date.now() + 86400000 }, { upsert: true, new: true }).exec(function(err, new_admin) {
                    done(err, token, new_admin);
                  });
                },
                function(token, admin, done) {
                  var data = {
                    to: admin.email,
                    from: email,
                    subject: 'CROWD SOURCE SET PASSWORD ',
                    text: 'Click the below link to set password\n\n' +
                    'http://' + req.headers.host + '/admin/reset?token=' + token + '\n\n'
                  };
                  
                  smtpTransport.sendMail(data, function(err) {
                    if (!err) {
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
              message: "ADMIN PROFILE CREATED, MAIL HAS BEEN SENT TO REGISTERED ACCOUNT TO SET PASSWORD"
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



//API TO LOGIN ADMIN PROFILE

router.post("/login", (req, res, next) => {
  Admin.find({ email: req.body.email })
  .exec()
  .then(admin => {
    if (admin.length < 1) {
      return res.status(401).json({
        message: "Auth failed"
      });
    }
    bcrypt.compare(req.body.password, admin[0].password, (err, result) => {
      if (err) {
        return res.status(401).json({
          message: "Auth failed"
        });
      }
      
      if (result) {
        const success_token = jwt.sign
        (
          {
            email: admin[0].email,
            admin: admin[0]._id
          },
         process.env.JWT_KEY,
          {
              expiresIn: "1h"
          }
        );
         
        if (result) {
          Admin.updateOne({email:req.body.email},{$set : { lastLogin : minuteFromNow()}},function(err) {
              if(err) 
              {
                 throw err;
              }
             } )
            

             Admin.updateOne({email:req.body.email},{tokky:success_token},function(err) {
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





//API TO EDIT ADMIN PROFILE (passing email)

router.put("/email/:adminEmail", (req, res, next) => {
  const email = req.params.adminEmail; 
  Admin.updateOne({email}, req.body)
    .exec()
    .then(result =>{
      console.log(result);
      res.status(200).json({
          message: 'Admin details updated',
          request: {
              type: 'GET',
              url: 'http://localhost:3000/admin/email/'+ email
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



//API TO GET PARTICULAR ADMIN PROFILE DETAILS (passing email)

router.get("/email/:adminEmail", (req, res, next) => {
  const email = req.params.adminEmail;
  Admin.find({email})
    .select('_id email username firstname middlename lastname mobile creation_time lastLogin')
    .exec()
    .then(doc => {
      console.log("From database", doc);
      if (doc) {
        res.status(200).json({
            Admin_details: doc,
            request: {
                type: 'GET',
                url: 'http://localhost:3000/admin/email'
            }
        });
      } else {
        res
          .status(404)
          .json({ message: "No admin found for provided ID" });
      }
    })
    .catch(err => {
      console.log(err);
      res.status(500).json({ error: err });
    });
});



//API TO GET ALL ADMIN PROFILE DETAILS

router.get("/", (req, res, next) => {
    Admin.find()
 .select("_id email username firstname middlename lastname mobile creation_time lastLogin")
  .exec()
  .then(docs => {
    const response = {
      count: docs.length,
      admin: docs.map(doc => {
        return {
            Admin_details: doc,
          request: {
            type: "GET",
            url: "http://localhost:3000/admin/"+ doc._id   
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



//LOGOUT (DESTROY TOKEN)

router.get('/logout/:success_token', function(req, res) {
  Admin.findOne
  ({
  tokky: req.params.success_token
  })
.exec(function(err, admin) {
  if (!err &&admin)
   {      
      admin.tokky = undefined;
      
admin.save(function(err) {
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




//API TO DELETE ADMIN PROFILE(passing email)

router.delete("/delete/:adminEmail", (req, res, next) => {
  const email = req.params.adminEmail;
  Admin.deleteOne({email})
    .exec()
    .then(result => {
      res.status(200).json({
          message: 'Admin deleted',
          request: {
              type: 'DELETE',
              url: 'http://localhost:3000/admin',
              
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
        Admin.findOne({
        email: req.body.email
      }).exec(function(err, admin) {
        if (admin) {
          done(err, admin);
        } else {
          done('admin not found.');
        }
      });
    },
    function(admin, done) {
      // create the random token
      crypto.randomBytes(20, function(err, buffer) {
        const token = buffer.toString('hex');
        done(err, admin, token);
      });
    },
    function(admin, token, done) {
        Admin.findByIdAndUpdate({ _id: admin._id }, { reset_password_token: token, reset_password_expires: Date.now() + 86400000 }, { upsert: true, new: true }).exec(function(err, new_admin) {
        done(err, token, new_admin);
      });
    },
    function(token, admin, done) {
      
      var data = {
        to: admin.email,
        from: email,
        subject: 'CROWD SOURCE Password Reset',
        text: 'Click the below link to reset\n\n' +
        'http://' + req.headers.host + '/admin/reset?token=' + token + '\n\n'
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
  res.sendFile(path.resolve('./public/admin.html'));
  //server.use(express.static(path.join(__dirname, './static')));
});

router.post("/reset", (req, res, next) => {
    Admin.findOne({
    reset_password_token: req.body.token,
    reset_password_expires: {
      $gt: Date.now()
    }
  }
)
  .exec(function(err, admin) {
    if (!err &&admin) {
      
      if (req.body.newPassword === req.body.verifyPassword) {
        
        admin.password = bcrypt.hashSync(req.body.newPassword, 10);
        admin.reset_password_token = undefined;
        admin.reset_password_expires = undefined;
        admin.save(function(err) {
          if (err) {
            return res.status(422).send({
              message: err
            });
          } else {
            var data = {
              to: admin.email,
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
