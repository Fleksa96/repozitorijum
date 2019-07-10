from . import Config


class Development(Config):
    ENV_TYPE =  "Development"

    ENV_TYPE = False
    DB_NAME = "praksa_2019"
    DB_USER = "ime_name"
    DB_PASSWORD = "123"
    DB_HOST = "127.0.0.1"
    DB_PORT = 5432

    username = "Nikola"
    password = "123456"

    SQLALCHEMY_DATABASE_URI = f'postgresql+psycopg2://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}'
        