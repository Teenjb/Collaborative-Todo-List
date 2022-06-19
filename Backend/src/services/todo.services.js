const { resolve } = require('path');
const db = require('../configs/db.config');
const helper = require('../utils/helper');

async function login (todo){
    const {username, password} = todo;
    const query = `SELECT * FROM users WHERE username = '${username}'`;
    const result = await db.query(query);
    if(result.rows.length === 0){
        return {
            message: 'User not found'
        }
    }else{
        const user = result.rows[0];
        if(user.password === password){
            return {
                message: 'Login successful',
                user
            }
        }else{
            return {
                message: 'Password incorrect'
            }
        }
    }
}

async function register (todo){
    const {username, password} = todo;
    console.log(username, password);
    const pass = await helper.hashPassword(password);
    const query = `INSERT INTO users (username, password) VALUES ('${username}', '${pass}')`;
    const result = await db.query(query);
    if(result.rowCount > 0){
        return {
            message: 'User created successfully'
        }
    }else{
        throw new Error('Error creating user');
    }
}

async function grouplist (todo, user){
    const {name} = todo;
    var id;
    let response = [];
    let promises = [];
    if(user){
        let promise = new Promise((resolve, reject) => { 
            const query = `INSERT INTO grouplist (groupname) VALUES ('${name}') RETURNING grouplistid`;
            db.query(query).then(function(result){
                resolve(result.rows[0].grouplistid);
            });
        });
        return promise.then(function(value){
            const query = `INSERT INTO grouplist_user (grouplistid, userid) VALUES (${value}, ${user.userid})RETURNING grouplistid,userid`;
            promises.push(db.query(query).then(function(result){
                console.log(result);
                response.push(result.rows[0]);
                }));
        return Promise.all(promises).then(()=>{return {message: 'grouplist created', grouplistid: response[0].grouplistid, userid: response[0].userid}});
        });
    }else{
        return {
            message: 'Not loged in'
        }
    }
}

async function get_grouplist(user){
    if(user){
        const query = `SELECT * FROM grouplist NATURAL JOIN grouplist_user WHERE userid = ${user.userid}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: 'grouplist found',
                grouplist: result.rows
            }
        }else{
            return {
                message: 'grouplist not found'
            }
        }
    }else{
        return {
            message: 'Not loged in'
        }
    }  
}

async function join_grouplist(todo, user){
    const {grouplistid} = todo;
    if(user){
        const query = `INSERT INTO grouplist_user (grouplistid, userid) VALUES (${grouplistid}, ${user.userid})`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `grouplist ${grouplistid} joined`
            }
        }else{
            return {
                message: `cant join grouplist ${grouplistid}`
            }
        }
    }else{
        return {
            message: 'Not loged in'
        }
    }
}

async function create_list(todo){
    const {grouplistid,title,due,ischecked} = todo;
    const query = `INSERT INTO list (grouplistid, title, due, ischecked) VALUES (${grouplistid}, '${title}', '${due}', ${ischecked}) RETURNING listid`;
    const result = await db.query(query);
    if(result.rowCount > 0){
        return {
            message: `list ${title} created`,
            listid: result.rows[0].listid
        }
    }else{
        return {
            message: `cant create list`
        }
    }
}

async function get_list(todo){
    const {grouplistid} = todo;
    const query = `SELECT * FROM list WHERE grouplistid = ${grouplistid}`;
    const result = await db.query(query);
    if(result.rowCount > 0){
        return {
            message: `list found`,
            list: result.rows
        }
    }else{
        return {
            message: `list not found`
        }
    }
}

async function update_list(todo){
    const {listid,title,due,ischecked} = todo;
    const query = `UPDATE list SET title = '${title}', due = '${due}', ischecked = ${ischecked} WHERE listid = ${listid}`;
    const result = await db.query(query);
    console.log(result);
    if(result.rowCount > 0){
        return {
            message: `list ${listid} updated`
        }
    }else{
        return {
            message: `cant update list ${listid}`
        }
    }
}

async function delete_list(todo){
    const {listid} = todo;
    const query = `DELETE FROM list WHERE listid = ${listid}`;
    const result = await db.query(query);
    if(result.rowCount > 0){
        return {
            message: `list ${listid} deleted`
        }
    }else{
        return {
            message: `cant delete list ${listid}`
        }
    }
}

async function delete_grouplist(todo, user){
    const {grouplistid} = todo;
    if(user){
        const query = `DELETE FROM grouplist WHERE grouplistid = ${grouplistid}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `grouplist ${grouplistid} deleted`
            }
        }else{
            return {
                message: `cant delete grouplist ${grouplistid}`
            }
        }
    }else{
        return {
            message: 'Not loged in'
        }
    }
}

async function update_check(todo){
    const {listid,ischecked} = todo;
    const query = `UPDATE list SET ischecked = ${ischecked} WHERE listid = ${listid}`;
    const result = await db.query(query);
    if(result.rowCount > 0){
        return {
            message: `list ${listid} updated`
        }
    }else{
        return {
            message: `cant update list ${listid}`
        }
    }
}

async function update_password(todo, user){
    const {password} = todo;
    if(user){
        const hashedPassword = await helper.hashPassword(password);
        const query = `UPDATE users SET password = '${hashedPassword}' WHERE userid = ${user.userid}`;
        const result = await db.query(query);
        if(result.rowCount > 0){
            return {
                message: `password updated`
            }
        }else{
            return {
                message: `cant update password`
            }
        }
    }else{
        return {
            message: 'Not loged in'
        }
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
    update_check,
    update_password
}