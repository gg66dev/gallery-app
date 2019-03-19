CREATE TABLE image(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL CONSTRAINT image_name_unique UNIQUE,
  updateddate TIMESTAMP WITH TIME ZONE
);
-- index name
create index image_name_idx on image (name);
-- name must be unique

CREATE TABLE page(
  id SERIAL PRIMARY KEY,
  url VARCHAR(255) NOT NULL CONSTRAINT page_url_unique UNIQUE,
  image_id BIGINT,
  FOREIGN KEY (image_id) REFERENCES image (id)
);
-- index url
create index page_url_idx on page (url);
-- url must be unique

CREATE TABLE viewer(
  id SERIAL PRIMARY KEY,
  ip VARCHAR(255) NOT NULL CONSTRAINT  viewer_ip_unique UNIQUE,
  createddate TIMESTAMP WITH TIME ZONE,
  lastvisitdate TIMESTAMP WITH TIME ZONE
);
-- index ip
create index viewer_ip_idx on viewer (ip);
-- ip must be unique

CREATE TABLE pageview(
  id SERIAL PRIMARY KEY,
  viewer_id BIGINT,
  page_id BIGINT,
  islike BOOLEAN,
  isunlike BOOLEAN,
  numviews BIGINT,
  FOREIGN KEY (viewer_id) REFERENCES viewer (id),
  FOREIGN KEY (page_id) REFERENCES page (id)
);

CREATE TABLE comment(
  id SERIAL PRIMARY KEY,
  createddate TIMESTAMP WITH TIME ZONE NOT NULL,
  message VARCHAR(255) NOT NULL,
  pageview_id BIGINT,
  FOREIGN KEY (pageview_id) REFERENCES pageview (id)
);


