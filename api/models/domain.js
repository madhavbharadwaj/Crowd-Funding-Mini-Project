const mongoose = require('mongoose');


  const domainSchema = mongoose.Schema
    ({
    domain: { type: String, required: true },
    });

module.exports = mongoose.model('Domain', domainSchema);