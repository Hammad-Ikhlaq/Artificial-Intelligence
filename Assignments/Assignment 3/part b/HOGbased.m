img = imread('MNIST/pic.png');

[featureVector,hogVisualization] = extractHOGFeatures(img);

figure;
imshow(img); 
hold on;
plot(hogVisualization);