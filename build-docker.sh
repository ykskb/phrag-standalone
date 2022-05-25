rm -rf target
lein uberjar
docker build -t ykskb/phrag-standalone .
