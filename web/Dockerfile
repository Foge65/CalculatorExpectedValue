FROM node:23 AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

FROM busybox:1.37
WORKDIR /app
COPY --from=build /app/dist .
CMD ["busybox", "httpd", "-f", "-v", "-p", "80"]

