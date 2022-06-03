const passport = require('passport');
const express = require('express');
const router = express.Router();
const todoController = require('../controllers/todo.controllers');

router.get('/login',passport.authenticate('local'), todoController.login);

router.post('/register', todoController.register);

module.exports = router;