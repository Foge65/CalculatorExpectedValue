FROM node:23 AS build
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.27
WORKDIR /usr/share/nginx/html
COPY --from=build /app/dist ./
EXPOSE 8085
CMD ["nginx", "-g", "daemon off;"]
