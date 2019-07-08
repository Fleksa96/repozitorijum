from flask import Flask

from config.development import Development as config
from config.production import Production as config


def create_app(conf):
    app = Flask(__name__)
    app.config.from_object(conf)
    return app
    
app=create_app(config)


@app.route("/")
def hello():
    return "HELLO WORLD!asdsa"