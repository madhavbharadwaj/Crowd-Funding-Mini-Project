const express = require("express");
const router = express.Router();
const mongoose = require("mongoose");
const Domain = require("../models/domain");


//API TO ADD DOMAIN

router.post("/", (req, res, next) => {
    const domain = new Domain({
      domain: req.body.domain
         });
         domain
      .save()
      .then(result => 
        {
        console.log(result),
       res.status(201).json({
          message: "domain added successfully",
          DOMAIN_NAME: {
              domain: result.domain
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
  
//API TO GET ALL AVAILABLE DOMAIN

router.get("/", (req, res, next) => {
    Domain.find()
   .select("domain")
    .exec()
    .then(docs => {
      const response = {
        TOTAL_NO_OF_DOMAINS: docs.length,
            DOMAIN_DETAILS: docs
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


  module.exports = router;