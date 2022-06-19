const { session } = require('passport/lib');
const todoService = require('../services/todo.services');


async function login(req,res){
    try{
        //const result = await todoService.login(req.body);
        res.json({message: 'authorized'});
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

async function grouplist(req,res){
    try{
        const result = await todoService.grouplist(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function get_grouplist(req,res){
    try{
        const result = await todoService.get_grouplist(req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function join_grouplist(req,res){
    try{
        const result = await todoService.join_grouplist(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function create_list(req,res){
    try{
        const result = await todoService.create_list(req.body, req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function get_list(req,res){
    try{
        const result = await todoService.get_list(req.query);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function update_list(req,res){
    try{
        const result = await todoService.update_list(req.body);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function delete_list(req,res){
    try{
        const result = await todoService.delete_list(req.body);
        res.json(result);
    }catch(err){
        res.json(err);
    }
} 

async function delete_grouplist(req,res){
    try{
        const result = await todoService.delete_grouplist(req.query, req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function logout(req,res){
    try{
        if(req.user){
            req.session.destroy()
            res.clearCookie('connect.sid') // clean up!
            return res.json({ msg: 'logging you out' })
        }else{
            return res.json({ msg: 'no user to log out!' })
        }
    }
    catch(err){
        res.json(err);
    }
}

async function update_check(req,res){
    try{
        const result = await todoService.update_check(req.query);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

async function update_password(req,res){
    try{
        const result = await todoService.update_password(req.query, req.user);
        res.json(result);
    }catch(err){
        res.json(err);
    }
}

module.exports = {
    login,
    register,
    grouplist,
    get_grouplist,
    join_grouplist,
    create_list,
    get_list,
    update_list,
    delete_list,
    delete_grouplist,
    logout,
    update_check,
    update_password
}