const todoService = require('../services/todo.services');


async function login(req,res){
    try{
        //const result = await todoService.login(req.body);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function register(req,res){
    try{
        const result = await todoService.register(req.body);
        res.json(result);
    }catch(err){
        res.json(err.detail);
    }
}

module.exports = {
    login,
    register
}