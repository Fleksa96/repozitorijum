import os
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
import pandas as pd
#import time
#import threading

if os.environ['ENV_TYPE'] == 'Development':
    from config.development import Development as Conf
elif os.environ['ENV_TYPE'] == 'Production':
    from config.production import Production as Conf

db = SQLAlchemy()

"""class csvThread(threading.Thread):
    def __init__(self):
        super(csvThread, self).__init__()
        pass

    def export():
        measurements = db.session.query(Measurement).all()
        ret = []
        for measure in measurements:
            ret.append({ "air_quality" : measure.air_quality, "humidity" : measure.humidity, "temperature" : measure.temperature })
        
        df = pd.DataFrame(ret)
        df.to_csv('out.csv', index=False) 

    def run(self):
        while 1:
            time.sleep(10)
            export()


thread1 = csvThread()
thread1.start()"""


from marshmallow import ValidationError
from flask import jsonify
from measurement_bp import measurements_bp
from measurement_bp.models.Measurement import Measurement
from measurement_bp.models.User import User
from crontab import CronTab

def create_app(conf):
    app = Flask(__name__)
    app.config.from_object(conf)
    db.init_app(app)

    cron = CronTab(user=True)
    job = cron.new(command='python /home/Desktop/git/repozitorijum/utilsPackage/exporter.py')  
    job.minute.every(1)

    cron.write()

    app.register_blueprint(measurements_bp)

    return app
    

app = create_app(Conf)
migrate = Migrate(app, db)


@app.errorhandler(ValidationError)
def _handle_api__error(ex):
    return ex.normalized_messages()

@app.route("/")
def hello():
    return "HELLO WORLD!"
    

