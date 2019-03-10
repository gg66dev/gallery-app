CREATE TABLE image(
  id SERIAL,
  name VARCHAR(255),
  updateddate TIMESTAMP WITH TIME ZONE
);
-- index name

CREATE TABLE page(
  id SERIAL,
  url VARCHAR(255),
  image_id BIGINT,
  FOREIGN KEY (image_id) REFERENCES image (id)
);
-- index url

CREATE TABLE viewer(
  id SERIAL,
  ip VARCHAR(255),
  createddate TIMESTAMP WITH TIME ZONE,
  lastvisitdate TIMESTAMP WITH TIME ZONE
);
-- index ip

CREATE TABLE pageview(
  id SERIAL,
  viewer_id BIGINT,
  page_id BIGINT,
  like BOOLEAN,
  unlike BOOLEAN,
  FOREIGN KEY (viewer_id) REFERENCES viewer (id),
  FOREIGN KEY (page_id) REFERENCES page (id)
);

CREATE TABLE comment(
  id SERIAL,
  createddate TIMESTAMP WITH TIME ZONE,
  message VARCHAR(255),
  pageview_id BIGINT,
  FOREIGN KEY (pageview_id) REFERENCES pageview (id)
);


