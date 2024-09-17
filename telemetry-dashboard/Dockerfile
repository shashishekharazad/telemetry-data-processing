# Stage 1: Build the React app
FROM node:20 AS build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the React app
RUN npm run build

# Stage 2: Serve the app using Nginx
FROM nginx:alpine

# Copy the build files from the build stage
COPY --from=build /app/build /usr/share/nginx/html
#
## Copy SSL certificates
#COPY ./certs/telemetry-dashboard.crt /etc/nginx/ssl/telemetry-dashboard.crt
#COPY ./certs/telemetry-dashboard.key /etc/nginx/ssl/telemetry-dashboard.key

# Copy Nginx configuration file
COPY ./nginx.conf /etc/nginx/nginx.conf

# Expose port 443 for HTTPS
EXPOSE 443

# Run Nginx
CMD ["nginx", "-g", "daemon off;"]
