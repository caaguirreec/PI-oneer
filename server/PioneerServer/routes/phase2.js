var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('phase2view', { title: 'Pi-oneer Phase two' });
});

module.exports = router;/**
 * Created by Cesar on 09/03/2016.
 */