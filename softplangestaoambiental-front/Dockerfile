FROM node:latest as build
WORKDIR /usr/app
COPY . /usr/app
RUN npm ci
RUN npm run build

FROM nginx:1.23.1-alpine
EXPOSE 3000
COPY ./nginx/nginx.conf /etc/nginx/conf.d/default.conf
# COPY /usr/src/app/nginx/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/app/dist /usr/share/nginx/html