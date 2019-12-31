TestImages=csvread('MNIST/test.csv', 0, 0);
TrainingImages=csvread('MNIST/train.csv', 0, 0);
noOfTestImages=size(TestImages,1);
for i=1:noOfTestImages

singleTestImage = zeros(1,784);
singleTestImage(1,:) = TestImages(i,2:785);
%copying every dimension

k=3;
NeighboursID=ClassifyByEuclideanDistance(singleTestImage,TrainingImages,k);

Labels= zeros(1,k);
Labels(1,1:k)=TrainingImages(NeighboursID(1:k),1);
%getting corresponding labels

end