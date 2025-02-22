FROM node:23 AS build
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build
RUN npm install -g serve
CMD ["serve", "-s", "dist", "-l", "8085"]

