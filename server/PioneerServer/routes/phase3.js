var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('phase3view', { title: 'Pi-oneer Phase three' });
});

module.exports = router;/**
 * Created by Cesar on 09/03/2016.
 */
