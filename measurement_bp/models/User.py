import os
from app import db
from sqlalchemy import (Column,Integer,Float,String)
from datetime import datetime

class User(db.Model):
    __tablename__ = 'users'

    username = Column(String, primary_key = True)
    password = Column(String)