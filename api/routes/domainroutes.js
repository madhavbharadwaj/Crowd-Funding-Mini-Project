const express = require("express");
const router = express.Router();
const mongoose = require("mongoose");
const Domain = require("../models/domain");

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
  
  module.exports = router;