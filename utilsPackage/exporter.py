import pandas as pd
from measurement_bp.models.Measurement import Measurement
from app import db

def export():
    measurements = db.session.query(Measurement).all()
    ret = []
    for measure in measurements:
        ret.append({ "air_quality" : measure.air_quality, "humidity" : measure.humidity, "temperature" : measure.temperature })
     
    df = pd.DataFrame(ret)
    df.to_csv('out.csv') 