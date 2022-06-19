const passport = require('passport');
const express = require('express');
const router = express.Router();
const todoController = require('../controllers/todo.controllers');
const {spaceChecker} = require('../utils/helper');
const {validationMiddleware} = require('../middlewares/validation');

router.post('/login',passport.authenticate('local'), todoController.login);

router.post('/register', spaceChecker, validationMiddleware, todoController.register);

router.post('/create-grouplist', todoController.grouplist);

router.get('/get-grouplist', todoController.get_grouplist);

router.post('/join-grouplist', todoController.join_grouplist);

router.post('/create-list', todoController.create_list);

router.get('/get-list', todoController.get_list);

router.put('/update-list', todoController.update_list);

router.delete('/delete-list', todoController.delete_list);

router.delete('/delete-grouplist', todoController.delete_grouplist);

router.post('/logout', todoController.logout);

router.put('/update-check', todoController.update_check);

router.put('/update-password', todoController.update_password);

module.exports = router;