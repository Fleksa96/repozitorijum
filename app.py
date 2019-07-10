import os
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate


if os.environ['ENV_TYPE'] == 'Development':
    from config.development import Development as Conf
elif os.environ['ENV_TYPE'] == 'Production':
    from config.production import Production as Conf

db = SQLAlchemy()



from measurement_bp import measurements_bp
from measurement_bp.models.Measurement import Measurement

def create_app(conf):
    app = Flask(__name__)
    app.config.from_object(conf)
    db.init_app(app)

    app.register_blueprint(measurements_bp)

    return app
    
app = create_app(Conf)
migrate = Migrate(app, db)

@app.route("/")
def hello():
    return "HELLO WORLD!"