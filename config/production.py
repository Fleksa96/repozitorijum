from . import Config

class Production(Config):

    ENV_TYPE = "Production" 

    SQLALCHEMY_DATABASE_URI = os.environ["DATABASE_URL"]
    DB_NAME = None
    DB_USER = None
    DB_PASSWORD = None
    DB_HOST = None
    DB_PORT = None