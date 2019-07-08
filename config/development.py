from . import Config


class Development(Config):
    ENV_TYPE =  "Dev"


    DB_NAME = None
    DB_USER = None
    DB_PASSWORD = None
    DB_HOST = None
    DB_PORT = None