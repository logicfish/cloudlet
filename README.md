Cloudlets
========

Cloudlets - distributed application middleware framework


Status

Early beta.



Features


Massive distributed graph hashing service.  Connected applications export a graph segment, which is used for networking with the cloud.
Hashes may be shared using cloudlet connectors; for example, over HTTP, or by using a bitcoin-style P2P protocol.

Applications can export an automatically generated Domain Specific Language allowing scripts to easily be written,
and allows for code fragments to be transmitted as text over the network.  These may be signed and stored as hashes.

Automatic discovery of application enhancements and extensions.  Applications re-configure and upgrade themselves using a scripted environment.
Automatic integration of applications supporting common frameworks, for example REST-ful HTTP services and applications supporting SPARQL and RDF,
by using meta-data descriptors.

Extensible development environment allowing automatic integration of SDK kits, API's and connectors.
Intelligent code generation from templates using an agent-based decision loop.

Automatic generation of DSL language specifications and libraries based on graphs compiled from the meta-data imported by the application.
The application can then be easily scripted, and functions may be shared or downloaded using hased vectors in the global tree.


Built in key chains and signing functions.  Published hash vectors can be chained so that a client can only unencrypt the digest if it has a
key attached to the previous entry in the chain.  We can add new keys to the chain at any time, and revoke earlier ones.
The hashing can chaining is built into the servers by default; applications can export digest as clear-text with a signature, or as
encrypted binary data.   Basically, every hash key in a store has a binary BLOB attached which may be in any format.  We can
also match meta-data properties to the hash which are also signed.  Decoding the basic hash gives us the mime data; we can
download the signed content which we may need to decrypt using a pre-shared key.   Network connections are validated using a consensus model, whereby
a given number of trusted hosts must aggree that the certficate of any particular trusted host has a valid signature.




Example


Local caching server
This solution provides a local connection to cloudlets.  Various services are available depending on the local configuration.
The application hosts a local graph segment, parts of which may be exposed to the cloud.  
Remote services may also be integrated into the graph.
The store may have an integrated inferencing engine.



Browser add-on

Grease-monkey style browser plugin, using websockets to communicate with a local cache server, that also has a running web proxy.  
The server maintains a session context
and feeds the browser plugin relevant snippets of code from it's database, and also suggests extensions, modifications, and enhancements
by querying the cache store.
The cache can then interact directly with the user via the browser window; for example, it can monitor text input and come up with
suggestions based on previous choices, and the local context.


Agents
Agents generally run in their own separate process, and do not communicate with clients directly.  Their job is simply to manage the
meta-data in the cache stores which they do either as default clients, or by using privileged connections to specific meta-store servers.



Tools

Development Environment
Compiler 
SDK/API Agent

