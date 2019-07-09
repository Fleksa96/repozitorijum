
from flask import Flask
from flask_sqlalchemy import SQLAlchemy


from config.development import Development as config

db = SQLAlchemy()


def create_app(conf):
    app = Flask(__name__)
    app.config.from_object(conf)
    db.init_app(app)

    return app
    
app = create_app(config)


@app.route("/")
def hello():
    return "HELLO WORLD!"