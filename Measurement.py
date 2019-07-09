import os
from app import db
from sqlalchemy import (Column,Integer,Float,String)
from datetime import datetime

class Measurement(db.Model):
    __tablename__ = 'measurments'

    id = Column(Integer, primary_key = True)
    air_quality = Column(Float)
    humidity = Column(Float)
    temperature = Column(Float)
    time_stamp = Column(db.DateTime, nullable=False,default=datetime.utcnow)
 