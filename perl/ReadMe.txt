Prequisites: 
	Perl v5.14.2
	cpanm (to install perl dependencies) http://search.cpan.org/~miyagawa/App-cpanminus-1.7004/lib/App/cpanminus.pm#INSTALLATION

#for json-rpc demo
sudo cpanm JSON::RPC::LWP


#for rest demo
sudo cpanm REST::Client
#watchout out for Crypt::SSLeay dependency, it failed for me so i had to install it manually (various tutorials available online on how to do it for your specific OS).
#Macs may need to install sudo cpanm Mozilla:CA

#misc
sudo cpanm DateTime




