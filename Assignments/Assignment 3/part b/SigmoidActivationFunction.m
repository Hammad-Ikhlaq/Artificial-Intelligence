function g = SigmoidActivationFunction(x)

  g = zeros(size(x));

  g = 1.0 ./ ( 1.0 + exp(-x));
    
end