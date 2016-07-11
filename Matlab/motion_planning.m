
l1 = 10.8; l2 = 8.3; l3 = 11; %set lengths in cm s.

fid = fopen('path.txt','w');

syms alpha beta gamma;

%position vector
r = [l1 * cos(alpha) + l2*cos(alpha + beta) +  l3*cos(alpha + beta + gamma);
    l1*sin(alpha) + l2*sin(alpha + beta)+l3*sin(alpha + beta + gamma)];
%state states
q = [alpha; beta; gamma];

%jacobian, delta(r) = J delta(q)
J = jacobian(r, q);

%define cartesian path points here

x = [12 : 0.01 : 14];
y = [13 : -0.01 : 10];

%

qn = [0; pi/2 ; pi/3 ]; %set initial states for jacobian.

%find first point jacobian.

rGoal = [x(1); y(1)] ;

rn = subs(r, q, qn ); %substitute first qn value and find rstart.

while (norm(rGoal-rn) > 1E-4) %until we get a good solution iterate.
    
    
    qn = qn + pinv(subs(J,q,qn)) * (rGoal - rn); % delta(q) = Jinv * delta(r)
    rn = (subs(r,q,qn));
    
end
%here we have good joint states to set at the start point
disp('for first point Qn : ');

%save this joint state
[res values ok] = check_joints(qn)
fprintf(fid,'%d,%d,%d\n', values(1), values(2), values(3));
%break;


for i = 2 :  size(x, 2)

    clf
    
    rGoal = [x(i); y(i)]; %this is our goal position.
    rPrevious = rn;
    
    % we can get motor speeds also.
    % when arm is in rPrevious state, we can give this velocity and next Q
    % state to move and achive.
        qdots = pinv(subs(J, q, qn)) * (rGoal - rPrevious );
        
        %this qdots may be very small, to make it higher lets multiply by a
        %constant.
        maxVelocity = max(qdots);
        qdots = qdots * ( 3 / max(abs(qdots))); % here 3 is defined to maximum joint velocity.
        
        %output qdot for velocity .
        qdots
    %
    
    %here first qn is the last solution of previous co-ord.
    while (norm(rGoal-rn) > 1E-3) %until we get a good solution iterate.
        
        
        qn = qn + pinv(subs(J,q,qn)) * (rGoal - rn); % delta(q) = Jinv * delta(r)
        rn = (subs(r,q,qn));
        
    end
    %here we have found joint states for i th coords,
    %save them
    [res values ok] = check_joints(qn)
    fprintf(fid,'%d,%d,%d\n', values(1), values(2), values(3));
    
    armX = [0, l1*cos(qn(1)), l1*cos(qn(1)) + l2 * cos(qn(1) + qn(2)),l1*cos(qn(1)) + l2 * cos(qn(1) + qn(2)) + l3*cos(qn(1) + qn(2) + qn(3))];
    armY = [0, l1*sin(qn(1)), l1*sin(qn(1)) + l2 * sin(qn(1) + qn(2)), l1*sin(qn(1)) + l2 * sin(qn(1) + qn(2))+ l3*sin(qn(1)+qn(2)+qn(3)) ];
    
    plot(armX,armY,'LineWidth',4,...
    'MarkerSize',10);
    drawnow
    

end
fclose(fid);







