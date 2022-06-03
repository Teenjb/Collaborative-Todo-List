const {Client} = require('pg');
const db = new Client({
    user: 'fateen_sbd',
    host: 'fateen-sbd.postgres.database.azure.com',
    database: 'collabtodo',
    password: 'Network6eeks',
    port: 5432,
    sslmode: 'require',
    ssl: true
});

db.connect((err) => {
    if (err) {
        console.log(err);
        }
        else {
            console.log('Connected to the todo database');
        }
});

module.exports = db;