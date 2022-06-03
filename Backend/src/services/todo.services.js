const db = require('../configs/db.config');

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
    const query = `INSERT INTO users (username, password) VALUES ('${username}', '${password}')`;
    const result = await db.query(query);
    if(result.rowCount === 1){
        return {
            message: 'User created successfully'
        }
    }else{
        throw new Error('Error creating user');
    }
}

module.exports = {
    login,
    register
}