export ENV_TYPE=Production
web: gunicorn app:app
init: python manage.py db init
migrate: python manage.py db migrate
release: python manage.py db upgrade
