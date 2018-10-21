const express = require("express");
const router = express.Router();
const mongoose = require("mongoose");
const Category = require("../models/category");


//API TO ADD CATEGORY

router.post("/", (req, res, next) => {
    const category = new Category({
      category: req.body.category
         });
         category
      .save()
      .then(result => 
        {
        console.log(result),
       res.status(201).json({
          message: "category added successfully",
          CATEGORY_NAME: {
              category: result.category
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

  //API TO GET ALL AVAILABLE CATEGORY

router.get("/", (req, res, next) => {
    Category.find()
   .select("category")
    .exec()
    .then(docs => {
      const response = {
        TOTAL_NO_OF_CATEGORY: docs.length,
        CATEGORY_DETAILS: docs
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