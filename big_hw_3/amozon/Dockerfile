# FROM node:20-alpine AS build
# WORKDIR /app

# # Copy package files and install dependencies
# COPY package*.json ./
# RUN npm install

# # Copy the rest of the application source code
# COPY . .

# # Build the application for production
# RUN npm run build || :

# # Stage 2: Serve the application with Nginx
# FROM nginx:alpine
# WORKDIR /usr/share/nginx/html

# # Remove default Nginx static assets
# RUN rm -rf ./*

# # Copy the built files from the 'build' stage
# COPY --from=build /app/build /usr/share/nginx/html

# EXPOSE 80

# # By default, Nginx listens on port 80.
# # The container will need to be run with a port mapping, e.g., docker run -p 8080:80
# ENTRYPOINT ["nginx", "-g", "daemon off;"]

# 1. Базовый образ для Node.js
FROM node:18-alpine AS builder

# 2. Рабочая директория
WORKDIR /app

# 3. Копируем package.json и package-lock.json
COPY package*.json ./

# 4. Устанавливаем зависимости
RUN npm install

# 5. Копируем все файлы проекта
COPY . .

# 6. Собираем проект
RUN npm run build

# 7.  Базовый образ для Nginx (или другого веб-сервера)
FROM nginx:alpine

# 8. Удаляем конфигурацию Nginx по умолчанию
RUN rm -rf /usr/share/nginx/html/*

# 9. Копируем собранные файлы из этапа сборки
COPY --from=builder /app/dist /usr/share/nginx/html

# 10.  Добавляем конфигурацию Nginx для обработки корня (/)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# 11. Порт, на котором будет работать Nginx
EXPOSE 80

# 12.  Запускаем Nginx
CMD ["nginx", "-g", "daemon off;"]