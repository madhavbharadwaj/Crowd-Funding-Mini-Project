const mongoose = require('mongoose');


  const domainSchema = mongoose.Schema
    ({
        id: mongoose.Schema.Types.ObjectId,
    domain: { type: String, required: true },
    });

module.exports = mongoose.model('Domain', domainSchema);